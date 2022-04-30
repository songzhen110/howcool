
-- brew 安装mysql帮助文档 http://events.jianshu.io/p/24ea660385cd
-- 配置Mysql远程连接
-- 赋予某个用户权限 赋予权限格式：grant 权限 on 数据库对象 to 用户@IP(或者相应正则)
-- 注：可以赋予select,delete,update,insert,index等权限精确到某一个数据库某一个表。
GRANT ALL PRIVILEGES ON *.* TO '用户名'@'%' IDENTIFIED BY '密码' WITH GRANT OPTION;-- 这里表示赋予该用户所有数据库所有表（*.*表示所有表），%表示所有IP地址。

-- 刷新权限：
FLUSH PRIVILEGES;

-- 将root用户设置为所有地址可登录，原来是localhost表示只用本机可登录
update user set host='%' where user='root';

flush privileges;

alter user 'root'@'%' identified by 'root1234' password expire never;

alter user 'root'@'%' identified with mysql_native_password by 'root1234';

flush privileges;