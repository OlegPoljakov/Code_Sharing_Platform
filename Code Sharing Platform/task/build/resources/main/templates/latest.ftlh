<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
           <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <title>Latest</title>
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
    <#list snippets as snippet>
            <span id="load_date">${snippet.getDate()}</span>
            <pre id="code_snippet"><code>${snippet.getCode()}</code></pre>
            <!--<pre id="code_snippet">${snippet.getCode()}</pre>-->
    </#list>
</body>
</html>