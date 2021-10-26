#目标
> 通过学习掌握博客系统串联技术栈

# 生成密钥证书文件
> keytool -genkeypair -alias oauth2 -keyalg RSA -keypass oauth2 -keystore oauth2.jks -storepass oauth2

# 获取公钥命令(密钥证书文件所在目录下执行)
> keytool -list -rfc --keystore oauth2.jks | openssl x509 -inform pem -pubkey

# docker打包镜像
> mvn clean package docker:build
> ***
> 注意：若使用阿里云服务器进行项目部署，需开放项目端口