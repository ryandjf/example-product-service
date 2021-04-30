package net.thoughtworks.archtest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

import org.junit.runner.RunWith;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "net.thoughtworks")
public class LayerDependencyRulesTest {

    @ArchTest
    public static final ArchRule domain_should_not_access_application = noClasses()
            .that().resideInAPackage("..domain..")
            .should().accessClassesThat().resideInAPackage("..application..");

    @ArchTest
    public static final ArchRule domain_should_not_access_infrastructure = noClasses()
            .that().resideInAPackage("..domain..")
            .should().accessClassesThat().resideInAPackage("..infrastructure..");

    @ArchTest
    public static final ArchRule domain_should_not_access_presentation = noClasses()
            .that().resideInAPackage("..domain..")
            .should().accessClassesThat().resideInAPackage("..presentation..");

    @ArchTest
    public static final ArchRule application_should_not_access_presentation = noClasses()
            .that().resideInAPackage("..application..")
            .should().accessClassesThat().resideInAPackage("..presentation..");

    @ArchTest
    public static final ArchRule application_should_not_access_infrastructure = noClasses()
            .that().resideInAPackage("..application..")
            .should().accessClassesThat().resideInAPackage("..infrastructure..");

    @ArchTest
    public static final ArchRule presentation_should_not_access_infrastructure = noClasses()
            .that().resideInAPackage("..presentation..")
            .should().accessClassesThat().resideInAPackage("..infrastructure..");
}
