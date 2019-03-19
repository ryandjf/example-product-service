#!/usr/bin/env bash

set +e

PROJECT_ROOT_PATH=${1:-.}
ISSUES_REPORT_FILE=hawkeye_report.json
ISSUES_REPORT_PATH=${PROJECT_ROOT_PATH}/build/reports/hawkeye
CONTAINER_UUID=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 8 | head -n 1)
CODE_CONTAINER_NAME="target-code-${CONTAINER_UUID}"
HAWKEYE_CONTAINER_NAME="hawkeye-code-${CONTAINER_UUID}"

function create_container_with_code() {
  docker create -v /target --name ${CODE_CONTAINER_NAME} alpine /bin/true;
  docker cp ${PROJECT_ROOT_PATH} ${CODE_CONTAINER_NAME}:/target;
}

function run_hawkeye_on_container_code() {
  docker run --volumes-from ${CODE_CONTAINER_NAME} --name ${HAWKEYE_CONTAINER_NAME} hawkeyesec/scanner-cli scan /target --show-code --json ${ISSUES_REPORT_FILE} --fail-on high
  hawkeye_return=$?
}

function create_artifacts_folder() {
  mkdir -p ${ISSUES_REPORT_PATH};
}

function copy_report_from_docker_remote_to_artifacts() {
  docker cp ${HAWKEYE_CONTAINER_NAME}:/target/${ISSUES_REPORT_FILE} ${ISSUES_REPORT_PATH}/${ISSUES_REPORT_FILE}
}

function remove_containers() {
  docker rm -f -v ${CODE_CONTAINER_NAME}
  docker rm -f -v ${HAWKEYE_CONTAINER_NAME}
}

create_container_with_code
run_hawkeye_on_container_code
create_artifacts_folder
copy_report_from_docker_remote_to_artifacts
remove_containers

if [ ${hawkeye_return} == 0 ]
then
    echo "Security checks passed"
else
    echo "Security checks failed. Report is available at ${ISSUES_REPORT_PATH}/${ISSUES_REPORT_FILE}."
fi

exit ${hawkeye_return}
