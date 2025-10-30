
### To add a file

Here is an example of adding a *cat.jpg* file i had in the directory

``
curl.exe -X POST "http://localhost:8080/api/s3/v1/upload/file" `
  -F "file=@C:\Users\Aeon\IdeaProjects\S3-Project\cat.jpg" `
  -F "name=cattos" `
  -F "description=cat image"
``

Was just a quick project to play around with springboot

---
Future upgrades if i ever comeback to it:

- Upgrade the Frontend where it has an empty dashboard , Login & register
- Add Cookies & limitations so that u can have a session limit
- Limit Users access to other User's storages
- Hash User info in the database
- Show the uploaded files with their names as like a list for each User , with a delete button close to it
- Find a better way to stare things rather than just saving them as that might take alot of space
- Add a size limit when storing in a nice interface