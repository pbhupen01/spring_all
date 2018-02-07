package contracts.userservice

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'POST'
        url '/users'
        body([
                value(
                        "userId": "test-user",
                        "name":"Test User",
                        "password":"test-user",
                        "emailId":"testUser@test.com"
                )
        ])
        headers {
            contentType('application/json')
        }
    }
    response {
        status 201
        body([
                "userId": "test-user",
                "name":"Test User",
                "emailId":"testUser@test.com"
        ])
        headers {
            contentType('application/json')
        }
    }
}