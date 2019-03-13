#!/usr/bin/env bash

set +e

ALPINE_VERSION=3.4
PROJECT_ROOT_PATH=${1:-.}
ISSUES_REPORT_FILE=hawkeye_report.json
ISSUES_REPORT_PATH=${PROJECT_ROOT_PATH}/build/reports/hawkeye

function create_container_with_code() {
  docker create -v /target --name target-code alpine:${ALPINE_VERSION} /bin/true;
  docker cp ${PROJECT_ROOT_PATH} target-code:/target;
}

function run_hawkeye_on_container_code() {
  docker run --volumes-from target-code --name hawkeye hawkeyesec/scanner-cli scan /target --show-code --json ${ISSUES_REPORT_FILE} --fail-on high
  hawkeye_return=$?
}

function create_artifacts_folder() {
  mkdir -p ${ISSUES_REPORT_PATH};
}

function copy_report_from_docker_remote_to_artifacts() {
  docker cp hawkeye:/target/${ISSUES_REPORT_FILE} ${ISSUES_REPORT_PATH}/${ISSUES_REPORT_FILE}
}

function remove_containers() {
  docker rm -f -v target-code
  docker rm -f -v hawkeye
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
