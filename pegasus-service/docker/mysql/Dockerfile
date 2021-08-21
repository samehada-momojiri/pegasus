FROM mysql:5.7

EXPOSE 3306

ADD ./conf.d/my.cnf /etc/mysql/conf.d/my.cnf

RUN chmod 644 /etc/mysql/conf.d/my.cnf

CMD ["mysqld"]
