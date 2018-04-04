<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <title>help desk</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <div class="page-header">
        <h1> HELP DESK <small>error</small></h1>
    </div>
    <div class="row">
        <div class="col-md-4">
            <img src="<c:url value='/resources/image/errorImg.jpg'/>" align="center" width="100%">
        </div>
        <div class="col-md-8">
            <h1>
                Ooops... Something wrong
            </h1>
        </div>
    </div>
</div>

</body>
</html>