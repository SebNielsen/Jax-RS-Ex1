<%-- 
    Document   : index
    Created on : 30-09-2015, 08:12:35
    Author     : sebastiannielsen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quotes</title>
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <div class="page-header">
                <p id="error"></p>
                <h1>Quote of today</h1>
                <h3 id="quote" class="text-muted"></h3>
            </div>
            <div class="panel panel-default">
                <div class="panel-group" id="accordion">
                    <div class="panel-heading" style="margin-left: -15px">
                        <label class="panel-title, btn btn-link">
                            <button class="btn btn-primary" id="random">Next Quote</button>
                            <a id= "quotes" data-toggle="collapse" class="btn btn-primary" data-parent="#accordion" 
                               href="#collapseOne">
                                See all quotes
                            </a>
                            <a data-toggle="collapse" class="btn btn-primary" data-parent="#accordion" 
                               href="#collapseTwo">
                                Create new Quote
                            </a>
                        </label>
                        <br>
                    </div>

                    <div id="collapseOne" class="panel-collapse collapse out">
                        <div class="panel-body">
                            <div class="col-sm-12">
                                <table class="table table-striped">
                                    <thead>
                                    <th>Id</th>
                                    <th>Quote</th>
                                    <th></th>
                                    <th></th>
                                    </thead>   
                                    <tbody id="tableBody">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div id="collapseTwo" class="panel-collapse collapse out">
                        <div class="panel-body">
                            <div class="col-sm-8">
                                <div class="col-sm-10">
                                    <input id = "newQuote" placeholder="New Quote:" class="form-control">
                                </div>
                                <div class="col-sm-2">
                                    <input style="float: right" id="create" type="submit" value="Create" class="btn btn-info">
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div id="collapseThree" class="panel-collapse collapse out">
                        <div class="panel-body">
                            <div class="col-sm-8">
                                <div class="col-xs-2">
                                    <input type="text" id = "selectedId" class="form-control" disabled>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" id = "selectedQuote" class="form-control">
                                </div>
                                <div class="col-sm-2">
                                    <input style="float: right" id="update" type="submit" class="btn btn-info" value="Update">
                                </div>
                            </div>
                        </div>
                    </div> 
                </div>
            </div>
        </div>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
            <script src="assets/js/javascript.js" type="text/javascript"></script>
            <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
