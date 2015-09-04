<html>
    <head>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    </head>

    <body style="text-align:center">
        <div align="center">
            <h2>New Client Form</h2>

            <form method="POST" action="checkStatus.html">

                <table>
                    <tr>
                        <td>Client name :</td>
                        <td><input name="name" id="name" /></td>
                    </tr>

                    <tr>
                        <td>Client surname :</td>
                        <td><input name="surname" id="surname"/></td>
                    </tr>

                    <tr>
                        <td>Address Line 1 :</td>
                        <td><input name="addressLine1" id="addressLine1"/></td>
                    </tr>

                    <tr>
                        <td>Address Line 2 :</td>
                        <td><input name="addressLine2" id="addressLine2"/></td>
                    </tr>

                    <tr>
                        <td>City :</td>
                        <td><input name="city" id="city"/></td>
                    </tr>

                    <tr>
                        <td>Country :</td>
                        <td><input name="country" id="country"/></td>
                    </tr>

                    <tr>
                        <td>Current Account Opening Balance :</td>
                        <td><input value="0" name="currentAccountOpeningBalance" id="currentAccountOpeningBalance"/></td>
                    </tr>

                    <tr>
                        <td>Saving Account Opening Balance :</td>
                        <td><input value="0" name="savingAccountOpeningBalance" id="savingAccountOpeningBalance"/></td>
                    </tr>

                    <tr>
                        <td><input type="submit"/></td>
                    </tr>
                </table>
            </form>

            <script type="text/javascript">
    
                $('form').submit(function () {
                	
                	
                	/* var data = {
                		name: document.getElementById('name').value,
                		surname: document.getElementById('surname').value,
                		addressLine1: document.getElementById('addressLine1').value,
                		addressLine2: document.getElementById('addressLine2').value
                	}; */
                	
                	
                    $.ajax({
                        url: $(this).attr('action'),
                        type: 'POST',
//                         data: data,// JSON.stringify($(this).serializeArray()),
						data : {
	                		name: document.getElementById('name').value,
	                		surname: document.getElementById('surname').value,
	                		addressLine1: document.getElementById('addressLine1').value,
	                		addressLine2: document.getElementById('addressLine2').value
	                	},
                        contentType: 'application/json',
                        success: function (data) {
                            alert('Client created!! :-)')
                        //    alert('dayta ' +data)
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                        	alert(errorThrown);
                            alert('An error has occured!! :-(')
                        }
                    })
                    return false
                })
            </script>
        </div>
    </body>
</html>