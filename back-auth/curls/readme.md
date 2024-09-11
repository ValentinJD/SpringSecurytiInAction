https://stackoverflow.com/questions/25957879/filter-order-in-spring-boot
порядок фильтров

Подключите ваш любимый удаленный отладчик к вашему приложению и установите точку останова
в doFilter(ServletRequest request, ServletResponse response) методе 
org.springframework.security.web.FilterChainProxy.

Начиная с Spring Security 5.1.6, это строка 311. В вашем отладчике узнайте существующие фильтры, 
проверив this.additionalFilters. В моем приложении порядок был примерно таким: