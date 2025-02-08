package co.wedevx.digitalbank.automation.ui.runners;

import io.cucumber.core.backend.Glue;
import org.junit.Test;
import org.junit.platform.suite.api.*;

import javax.annotation.processing.SupportedAnnotationTypes;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("ui/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "co.wedevx.digitalbank.automation.ui.steps")
@IncludeTags("Test")
public class UiRegressionRunner {



}
