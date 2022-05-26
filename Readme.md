# 如何隐式的将数据传递给RequestInterceptor
- By Global 利用全局变量
- By Thread 利用ThreadLocal, 如Spring的RequestContextHolder，SecurityContextＨolder
- By Request　利用@RequestHeader("X-Temp-Parameter-abc") 或　＠RequestParameter("x-temp-parameter-abc") 