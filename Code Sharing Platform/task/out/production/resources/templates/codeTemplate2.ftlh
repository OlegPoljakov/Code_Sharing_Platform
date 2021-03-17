<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
           <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <title>Code</title>
    <style type="text/css">
               span {
                font-family: Verdana, Arial, Helvetica, sans-serif;
                color: #333366;
               }
               pre {
                border: 2px solid black;
                background-color:lightgray;
                margin: 5px;
                }
     </style>
</head>

<body>

<span id="load_date">${responseCode.getDate()}</span>

<#if (responseCode.isViewLimit())>
    <p>
        <span id="views_restriction">${responseCode.getViews()}</span> more views allowed
    </p>
</#if>

<#if (responseCode.isTimeLimit())>
    <p>
        The code will be available for <span id="time_restriction">${responseCode.getTime()}</span> seconds
    </p>
</#if>

<pre id="code_snippet"><code>${responseCode.getCode()}</code></pre>

</body>
</html>
