package com.xinan.casServer.config;
 
import com.xinan.casServer.model.MyCredential;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConstants;
import org.apereo.cas.web.flow.configurer.DefaultLoginWebflowConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.engine.ViewState;
import org.springframework.webflow.engine.builder.BinderConfiguration;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

 
/**
 * 增加验证码
 *
 */
public class CaptchaWebflowConfigurer extends  DefaultLoginWebflowConfigurer {
	
	public CaptchaWebflowConfigurer(FlowBuilderServices flowBuilderServices,
			FlowDefinitionRegistry flowDefinitionRegistry, ApplicationContext applicationContext,
			CasConfigurationProperties casProperties) {
		super(flowBuilderServices, flowDefinitionRegistry, applicationContext, casProperties);
	}
 
	@Override
	protected void createRememberMeAuthnWebflowConfig(Flow flow) {
		 if (casProperties.getTicket().getTgt().getRememberMe().isEnabled()) {
	            createFlowVariable(flow, CasWebflowConstants.VAR_ID_CREDENTIAL, MyCredential.class);
	            final ViewState state = getState(flow, CasWebflowConstants.STATE_ID_VIEW_LOGIN_FORM, ViewState.class);
	            final BinderConfiguration cfg = getViewStateBinderConfiguration(state);
	            cfg.addBinding(new BinderConfiguration.Binding("rememberMe", null, false));
	            //增加验证码
	            cfg.addBinding(new BinderConfiguration.Binding("captcha", null, true));
	        } else {
	            createFlowVariable(flow, CasWebflowConstants.VAR_ID_CREDENTIAL, MyCredential.class);
	            final ViewState state = getState(flow, CasWebflowConstants.STATE_ID_VIEW_LOGIN_FORM, ViewState.class);
	            final BinderConfiguration cfg = getViewStateBinderConfiguration(state);
	            // cfg.addBinding(new BinderConfiguration.Binding("rememberMe", null, false));
			 //增加验证码
	            cfg.addBinding(new BinderConfiguration.Binding("captcha", null, true));
	        }
	}
 
}