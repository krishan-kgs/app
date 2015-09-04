<html>
    <head>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    </head>

    <body style="text-align:center">
        <div align="center">
            <h2>Submit Your Details Here</h2>

            <form method="POST" action="checkStatus" id="ajaxform" name="ajaxform">

                <table>
                    <tr>
                        <td>From  :</td>
                        <td><input name="fromAddr" id="fromAddr" /></td>
                    </tr>

                    <tr>
                        <td>To :</td>
                        <td><input name="toAddr" id="toAddr"/></td>
                    </tr>

                    <tr>
                        <td>Subject :</td>
                        <td><input name="subject" id="subject"/></td>
                    </tr>

                    <tr>
                        <td>Content :</td>
                        <td><input name="content" id="content"/></td>
                    </tr>

                    <tr>
                        <td>Remarks :</td>
                        <td><input name="comment" id="comment"/></td>
                    </tr>

                    <tr>
                        <td><input type="button" id="simplepost" value="Run"/></td>
                    </tr>
                </table>
            </form>

		<script type="text/javascript">
		//$(document).ready(function(){
			$("#simplepost").click(function(e)
			{
			var MyForm = $("#ajaxform").serialize();
			console.log(MyForm);
			 $.ajax(
			 {
			 url : "/app/checkStatus",
			 type: "POST",
			 data : MyForm,
			 dataType:"json",
			 success:function(maindta)
			 {

			alert(maindta);

			 },
			 error: function(jqXHR, textStatus, errorThrown)
			 {
			 }
			 });
			 e.preventDefault(); //STOP default action

			});
			// });
		
		</script>
	</div>
    </body>
</html>