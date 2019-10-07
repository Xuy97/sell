<html>
<head>
    <meta charset="UTF-8">
    <table>错误提示</table>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    成功!
                </h4> <strong>${msg!""}</strong>&nbsp;<a href="${url}" class="alert-link">3s后自动跳转</a>
            </div>
        </div>
    </div>
</div>
</body>

<script>
    setTimeout('location.href = "${url}"',3000)
</script>
</html>