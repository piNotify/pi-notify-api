package dev.pilati.pinotify.api

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class PiNotifyCORSConfiguratioin{
    
    @Bean 
    fun corsConfigurer(): WebMvcConfigurer{
        return object: WebMvcConfigurer{
            override fun addCorsMappings(registry: CorsRegistry){
                registry.addMapping("/**")
                    .allowedOrigins("*")
            }
        }
    }
}