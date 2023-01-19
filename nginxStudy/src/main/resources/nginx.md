
#user  nobody;
# 启动的worker进程数
# 可以通过 tasklist | findstr nginx 或者 ps -ef | grep nginx 查看进程数变化
# 通常情况下，我们会把 worker 进程数会设置成系统的 cpu 核数
worker_processes  8;  

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;

# 设置每个worker进程的最大连接数，它决定了Nginx的并发能力
events {
worker_connections  1024;
}


http {
include       mime.types;
default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;
	
	# 重要参数，是一个请求完成之后还要保持连接多久，不是请求时间多久，
	# 目的是保持长连接，减少创建连接过程给系统带来的性能损耗
    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    server {
		# 监听80端口
        listen       80;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            root   html;
            index  index.html index.htm;
			proxy_pass http://localhost:8080;
        }
        # 访问报错
        location /internal_error {
            return 500;
        }
        # (An error occurred.)
        # (Sorry, the page you are looking for is currently unavailable.)
        # (Please try again later.)
        # ()
        # (If you are the system administrator of this resource then you should check the error log for details.)
        # ()
        # (Faithfully yours, nginx.)
        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }


    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}


    # HTTPS server
    #
    #server {
    #    listen       443 ssl;
    #    server_name  localhost;

    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_cache    shared:SSL:1m;
    #    ssl_session_timeout  5m;

    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers  on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}

}
