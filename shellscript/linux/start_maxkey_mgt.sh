#!/bin/bash
source ./set_maxkey_env.sh

JAVA_MARK=MaxKeyMgt
JAVA_OPTS=" -Xms128m "
JAVA_OPTS="${JAVA_OPTS} -Xmx1024m"
JAVA_OPTS="${JAVA_OPTS} -Dfile.encoding=UTF-8"
JAVA_OPTS="${JAVA_OPTS} -DjavaMark=${JAVA_MARK}"

JAVA_CONF=./maxkey_mgt
JAVA_LIBPATH=./lib
JAVA_LIBMAXKEYPATH=./maxkey_mgt
JAVA_CLASSPATH=./classes:./bin:$JAVA_CONF:
JAVA_MAINCLASS=org.dromara.maxkey.MaxKeyMgtApplication
JAVA_EXEC=$JAVA_HOME/bin/java

export JAVA_CLASSPATH
export JAVA_LIBPATH
export JAVA_LIBMAXKEYPATH

for LL in `ls $JAVA_LIBPATH/*.jar`
        do
                JAVA_CLASSPATH=$JAVA_CLASSPATH:$LL
               
done

for LL in `ls $JAVA_LIBMAXKEYPATH/*.jar`
        do
                JAVA_CLASSPATH=$JAVA_CLASSPATH:$LL
               
done

export JAVA_CLASSPATH
# Display our environment
echo "-------------------------------------------------------------------------------"
echo "  Bootstrap Environment"
echo ""
#echo JAVA_CLASSPATH :  ${JAVA_CLASSPATH}
echo JAVA_CONF      :  $JAVA_CONF
echo JAVA_OPTS      :  $JAVA_OPTS
echo JAVA_HOME      :  $JAVA_HOME  
echo JAVA           :  $JAVA_EXEC}
echo ""
echo "-------------------------------------------------------------------------------"
echo ""

$JAVA_EXEC $JAVA_OPTS -classpath $JAVA_CLASSPATH $JAVA_MAINCLASS

echo run finished