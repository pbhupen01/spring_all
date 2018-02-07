package contracts.userservice

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/users/test-user'
        headers {
            contentType('application/json')
        }
    }
    response {
        status 200
        body([
                value(
                        "userId": "test-user",
                        "name":"Test User",
                        "emailId":"testUser@test.com"
                )
        ])
        headers {
            contentType('application/json')
        }
    }
}