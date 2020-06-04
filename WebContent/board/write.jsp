<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/nav.jsp"%>
<%@ include file="../include/authentication.jsp"%>
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<div class="container">

<form action="/blog/board?cmd=writeProc" method="post">
	<div class="form-group">
		<label for="title">title:</label> 
		<input type="text" class="form-control" placeholder="title" id="title" name="title">
	</div>
	<div class="form-group">
		<label for="content">content:</label>
		<textarea class="form-control" rows="5" id="summernote" name="content"></textarea>
	</div>
	<button type="submit" class="btn btn-primary">글쓰기 등록</button>
</form>
</div>
  <script>
    $(document).ready(function() {
        $('#summernote').summernote({
            tabsize: 2,
            height: 300
        });
    });
  </script>
<%@ include file="../include/footer.jsp"%>