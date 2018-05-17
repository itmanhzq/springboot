package com.chp;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @ClassName Application
 * @author Zhang.Tao
 * @Date 2017年4月27日 下午5:30:34
 * @version V2.0.0
 */
@RestController
@SpringBootApplication(exclude = MybatisAutoConfiguration.class)
@ServletComponentScan
@EnableSwagger2
@EnableAutoConfiguration
@MapperScan("com.chp.modules.*.dao.mapper")
public class ChpSpringbootApplication  extends SpringBootServletInitializer 
  implements EmbeddedServletContainerCustomizer {
  @Bean
  public Docket createRestApi() {
      return new Docket(DocumentationType.SWAGGER_2)
              .apiInfo(apiInfo())
              .select()
              .apis(RequestHandlerSelectors.basePackage("com.chp.web")) //扫描API的包路径
              .paths(PathSelectors.any())
              .build();
  }
  private ApiInfo apiInfo() {
      return new ApiInfoBuilder()
              .title("SpringBoot整合Swagger2") // 标题
              .description("api接口的文档整理，支持在线测试") // 描述
              .termsOfServiceUrl("http://vector4wang.tk/") //网址
              .contact("chp") // 作者
              .version("1.0") // 版本号
              .build();
  }
	@Value("${server.port}")
	private int port;//应用的端口
	/**
	 * 启动入口
	 * @param args
	 */
    public static void main(String ... args){
        SpringApplication.run(ChpSpringbootApplication.class, args);
    }

    /**
     * 自定义端口
     */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(port);
	}

}
