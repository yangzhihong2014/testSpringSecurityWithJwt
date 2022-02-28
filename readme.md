spring security 和 JWT</br>
使用基于json的jwt后 ，通过过滤器在每次请求前查看token 是否有效，以及装载当前请求中的用户信息，权限等等。

在`protected void configure(HttpSecurity http) throws Exception` 方法中设置禁用session，则在每次请求之后只能使用token校验请求`http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);`

继承`OncePerRequestFilter`类，重写`protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)`方法，在每次请求之前从header中获取token 信息，然后装载用户相关信息，如:角色、权限等等

登陆方法中，当用户名以及密码正确时，使用工具类将可以展示的信息生成token

