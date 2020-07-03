#!/bin/bash
version="1.0.1";

appName="family-qa.jar"

function start()
{
	count=`ps -ef |grep java|grep $appName|wc -l`
	if [ $count != 0 ];then
		echo "Maybe $appName is running, please check it..."
	else
		echo "The $appName is starting..."
		nohup java -jar ./$appName -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -Xms2G -Xmx2G > /dev/null 2>&1 &
	fi
}

function stop()
{
	appId=`ps -ef |grep java|grep $appName|awk '{print $2}'`
	if [ -z $appId ];then
	    echo "Maybe $appName not running, please check it..."
	else
        echo "The $appName is stopping... $appId"
        kill $appId
	fi
}

function restart()
{

    stop
    int=1
	  while [ 1 ];
	  do
        sleep 1
        appId=`ps -ef |grep java|grep $appName|awk '{print $2}'`
#        echo $int
        if [ -z $appId ]; then
            break
        fi
        echo "watfor $int second ......"
        let "int++"
        if [ $int -gt 10 ]; then
          echo "超时强制退出"
          kill -9 $appId
          break
        fi
	  done

    start
}

function status()
{
  appId=`ps -ef |grep java|grep $appName|awk '{print $2}'`
	if [ -z $appId ]
	then
	    echo -e "\033[31m Not running \033[0m"
	else
	    echo -e "\033[32m Running [$appId] \033[0m"
	fi
}


function usage()
{
    echo "Usage: $0 {start|stop|restart|status|stop -f}"
    echo "Example: $0 start"
    exit 1
}

case $1 in
	start)
	start;;

	stop)
	stop;;

	restart)
	restart;;

	status)
	status;;

	*)
	usage;;
esac