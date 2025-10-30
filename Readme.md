
### To add a file

Here is an example of adding a *cat.jpg* file i had in the directory

``
curl.exe -X POST "http://localhost:8080/api/s3/v1/upload/file" `
  -F "file=@C:\Users\Aeon\IdeaProjects\S3-Project\cat.jpg" `
  -F "name=cattos" `
  -F "description=cat image"
``