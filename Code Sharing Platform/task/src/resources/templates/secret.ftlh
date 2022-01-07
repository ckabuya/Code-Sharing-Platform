<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Code</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
       #load_date{
        color:green;
      }
      pre {
        background-color: #eee;
        border: 1px solid #999;
        display: block;
        padding: 20px;
      }
     </style>
    <link rel="stylesheet"
          target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<span id="load_date">${date}</span> <br/>
<#if views gt 0 || isSecret >
<span  id="views_restriction" >${views} more views allowed</span><br/>
</#if>
<#if time gt 0>
<span  id="time_restriction" >The code will be available for ${time} seconds</span> <br/>
</#if>
<pre id="code_snippet"><code>${code}</code></pre>
</body>
</html>