# 如何隐式的将数据传递给RequestInterceptor
- By Global 利用全局变量，配置文件
- By Thread 利用ThreadLocal, 如Spring的RequestContextHolder，SecurityContextＨolder
- By Request　利用@RequestHeader("X-Temp-Parameter-abc") 或　＠RequestParameter("x-temp-parameter-abc")

# 如何获取ApplicationContext
- 通过@Autowired注入到Bean（属性，方法，构造器）
- 通过ApplicationContextAware注入到Bean
- 通过静态方法ContextLoader.getCurrentWebApplicationContext()直接获取

# 如何更便捷的获取Cookie等Request以及Servlet相关的数据
- 建议使用org.springframework.web.util.WebUtils

# Http status code:
- 401, 403, 404, 405, 413 属于框架层面
- 502, 503 属于Gateway特有
- 200, 400, 500 属于应用层