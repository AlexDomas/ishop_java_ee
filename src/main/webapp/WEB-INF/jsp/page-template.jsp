<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>IShop Products</title>


        <link href="/static/css/bootstrap.css" rel="stylesheet">
        <link href="/static/css/bootstrap-theme.css" rel="stylesheet">
        <link href="/static/css/font-awesome.css" rel="stylesheet">
        <link href="/static/css/app.css" rel="stylesheet">
        <link href="/static/css/shoppingcart.css" rel="stylesheet">
        <link href="/static/css/about-us.css" rel="stylesheet">
        <link href="/static/css/add-delete-page.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=News+Cycle&family=Roboto+Slab:wght@300;400&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Galada&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Kaushan+Script&family=Signika:wght@500&family=Style+Script&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Slabo+27px&display=swap" rel="stylesheet">
        
        


    </head>
    <body>




    <header1>
        <jsp:include page="fragment/header.jsp" />
    </header1>

    <article class="art hidden-xs">

        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
            </ol>

            <!-- Wrapper for slides -->

            <div class="carousel-inner">
                <div class="item active">
                    <img src="/media/zenbook.jpg" alt="" />
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Asus ZenBook 13 UX325EA-KG262</h1>
                            <p class="lead">The new ZenBook 13 is thinner, lighter and more mobile, while still offering a complete set of interfaces</p>
                        </div>
                    </div>
                </div>

                <div class="item">
                    <img src="/media/honor.jpg" alt="" />
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Honor 20 Pro</h1>
                            <p class="lead">Honor 20 Pro turned out to be more strict and solid than most of its relatives in the Honor family</p>
                        </div>
                    </div>
                </div>

                <div class="item">
                    <img src="/media/omega.jpg" alt="" />
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Bootstrap Carousel Fullscreen</h1>
                            <p class="lead">Just turns the Twitter Bootstrap Carousel in fullscreen mode, and scale to fit the screen resolution</p>
                        </div>
                    </div>
                </div>

                <div class="item">
                    <img src="/media/stoneswatch.jpg" alt="" />
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Bootstrap Carousel Fullscreen</h1>
                            <p class="lead">Just turns the Twitter Bootstrap Carousel in fullscreen mode, and scale to fit the screen resolution</p>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </div>

    </article>

    <div class="container-fluid">

        <div class="row">
            <aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">

                <jsp:include page="fragment/aside.jsp" />
            </aside>

            <main class="col-xs-12 col-sm-8 col-md-9 col-lg-10">

                <jsp:include page="${currentPage}" />
            </main>

        </div>

    </div>

    <footer>
        <jsp:include page="fragment/footer.jsp" />

    </footer>

    <script src="/static/js/jquery.js"></script>
    <script src="/static/js/bootstrap.js"></script>
    <script src="/static/js/app.js"></script>
</body>
</html>