请求url：http://127.0.0.1:8080/xml/test
请求参数：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<service version="2.0">
    <BODY>
        <TX_CODE attr="s,10">facecompare</TX_CODE>
        <Name attr="s,10">facecompare</Name>
        <IdentNo attr="s,10">99999</IdentNo>
        <IdentPhtFilePath attr="s,255">1.jpg</IdentPhtFilePath>
        <SpotPhtFilePath attr="s,255">1.jpg</SpotPhtFilePath>
        <OvlapPhtFlg attr="s,255">1</OvlapPhtFlg>
        <CnlNo attr="s,255">12</CnlNo>
    </BODY>
    <SYS_HEAD>
        <TRAN_TIMESTAMP attr="s,6">153907</TRAN_TIMESTAMP>
        <CONSUMER_SEQ_NO attr="s,42">137000140118100000042563</CONSUMER_SEQ_NO>
        <WS_ID attr="s,30">p5</WS_ID>
        <SERVICE_SCENE attr="s,2">01</SERVICE_SCENE>
        <CONSUMER_ID attr="s,6">137000</CONSUMER_ID>
        <SERVICE_CODE attr="s,11">11002000018</SERVICE_CODE>
        <TRAN_DATE attr="s,8">20140118</TRAN_DATE>
    </SYS_HEAD>
    <APP_HEAD>
        <USER_ID attr="s,30">00120242</USER_ID>
        <PER_PAGE_NUM attr="s,3"></PER_PAGE_NUM>
        <QUERY_KEY attr="s,256"></QUERY_KEY>
        <BRANCH_ID attr="s,9">0135</BRANCH_ID>
    </APP_HEAD>
</service>
```
返回结果：
```xml
<service version="2.00">
    <BODY>
        <TX_CODE attr="s,103">facecompare4</TX_CODE>
        <Name attr="s,101">facecompare2</Name>
    </BODY>
    <SYS_HEAD>
        <TRAN_TIMESTAMP attr="s,6">153907</TRAN_TIMESTAMP>
    </SYS_HEAD>
</service>
```