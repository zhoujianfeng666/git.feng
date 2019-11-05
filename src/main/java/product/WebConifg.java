package product;

import javax.servlet.ServletContext;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import product._config.MyHandlerInterceptor;
import product._config.StringToDate;

@MapperScan("product.dao")
@Configuration
@EnableWebMvc
public class WebConifg implements WebMvcConfigurer {
	@Autowired
	ServletContext context;
	private static String projectRootRealPath;

	public static String getProjectRootRealPath() {
		return projectRootRealPath;
	}

	// 配置 路径的前后缀
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		projectRootRealPath = context.getRealPath("");
		// 确保项目 路径是以/结尾
		if (!projectRootRealPath.endsWith("/") || projectRootRealPath.endsWith("\\")) {
			projectRootRealPath = projectRootRealPath + "/";
		}

		System.out.println("-------------------路径装饰 注册成功---------------");
		registry.enableContentNegotiation(new MappingJackson2JsonView());
		registry.jsp("/WEB-INF/jsp/", ".jsp");// jsp("/WEB-INF/", ".jsp");
	}

	// 可以直接 在此注册 我们的 路径映射
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		建立映射关系
		// 跳转：当我们访问/forward/student/studentAdd时，它会自动【跳转】到/student/studentAdd
		registry.addViewController("/forward/product/add").setViewName("/student/studentAdd");
		// 重定向
		registry.addRedirectViewController("/abc", "https://www.baidu.com");
	}

	// 资源管理
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/cstatic/**")// 客户端访问资源的路径
				.setCachePeriod(1024 * 1024 * 10)// 缓存池大小
				.addResourceLocations("/public", "classpath:/cstatic/")// 本地（服务器）资源路径
		;
//		
		registry.addResourceHandler("/webjars/**")// 客户端访问资源的路径
				.setCachePeriod(1024 * 1024 * 10)// 缓存池大小
				.addResourceLocations("/public", "classpath:/META-INF/resources/webjars/").resourceChain(false)// 开发时，选用false；发部产品时，选有true:表示缓存
				.addResolver(new WebJarsResourceResolver()).addResolver(new PathResourceResolver())
		// 本地（服务器）资源路径
		;

		registry.addResourceHandler("/static/**")// 客户端访问资源的路径
				.setCachePeriod(1024 * 1024 * 10)// 缓存池大小
				.addResourceLocations("/public", "/static/")// 本地（服务器）资源路径
		;

	}

	// 类型转换：注册自己的类型转换实现
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToDate());
	}

//	拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/product/**");
	}

	// 后台校验 执行工厂类——提供校验的
	@Override
	public Validator getValidator() {
		return new org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean();
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.mediaType("json", MediaType.APPLICATION_JSON);
		configurer.mediaType("xml", MediaType.APPLICATION_XML);
	}
}
