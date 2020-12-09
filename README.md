<h1>First of all I would like to thank for being able to participate of the selective procces!</h1>

<h2>Greetings, Developer(s)!</h2>

Here I'm gonna tell a lit bit more of the decisions I took 
whereas I was doing the project.

I decided to use restful API, I developed the documentation you can access
in the link http://localhost:8080/documentation, you can have access of the api of this documentation using
 http://localhost/api-documentation.

I developed the CI using a lib called jib for creating and delivering the docker image to
docker registry and I did an integration with the sonarqube open source plataform for analyzing the project overall.

I'm using ORM for making easier the default queries of the entities, I'm using DTO
for some reasons, It's safer, faster and consume less memory when it comes to serialization e 
deserialization of big and complex objects.

I developed some important tests, I sorry for not doing more tests as I should, but 
because of the time I said I would develop, it made me to choose to deliver it
without implementing eveything the way I supossed to do.

I'm using HATEOAS which is a method for developing apis taking responsability of 
some rules that could be in the client side, of course, it's a trade off when it comes to 
complexity for developing it, but sometimes it can be necessary for
improving the end user experience.

Feel free to call me for a conversation about methodologies useds, libs, archiceture and anything else. 
