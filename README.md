# About GithubProxy
Project allows get information about repositories for given user.


# Running using Docker
To run project simply use Docker

``docker build --tag github-proxy .``

and then

``docker run -d -p 8080:8080 github-proxy``

# Running using Maven
You can use maven to run project:
``mvn compile``

and then:

``mvn exec:java``

# Sample usage:

``curl http://localhost:8080/blue0rabbit``

where `blue0rabbit` stands for username