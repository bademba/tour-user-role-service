## User Role Service
This service handles all user roles

1. **Fetch All User Roles**

GET localhost:9194/v2/tour/users/user-role

Response
``` 
{
    "data": [
        {
            "email": "ben@mail.com",
            "id": "2609378318",
            "role": "ADMIN"
        },
        {
            "email": "anne@mail.com",
            "id": "2609778995",
            "role": "SUPER_ADMIN"
        }
    ],
    "message": "User Roles found",
    "responseId": "af029924-c619-4dc4-ae47-3ddf64832ab0",
    "status": 200,
    "timestamp": "07-09-2026 12:37:58"
}
```

2. **Create User role**

**POST** localhost:9194/v2/tour/users/user-role

Request

```
{
    "email": "ben@mail.com",
    "roles": "ADMIN"
}
```
Response

```
{
    "data": {
        "email": "ben@mail.com",
        "id": "2609378318",
        "role": "ADMIN"
    },
    "message": "UserRole created",
    "responseId": "1ffa37c8-8438-4f74-9258-9e2aebc2e594",
    "status": 201,
    "timestamp": "07-09-2026 12:33:17"
}
```

3. **Fetch single user role**

**GET** localhost:9194/v2/tour/users/user-role/anne@mail.com

Response
200 OK

``` 
{
    "data": {
        "email": "anne@mail.com",
        "id": "2609778995",
        "role": "READ_ONLY"
    },
    "message": "User details found",
    "responseId": "f3bcd9bf-9e1d-4a37-af34-0728c59b8378",
    "status": 200,
    "timestamp": "07-09-2026 12:11:31"
}
```
4. **Update User role**

**PUT** localhost:9194/v2/tour/users/user-role/anne@mail.com

Request
``` 
{
    "roles": "SUPER_ADMIN"
}
```

Response 200 OK

``` 
{
    "data": {
        "email": "anne@mail.com",
        "id": "2609778995",
        "role": "SUPER_ADMIN"
    },
    "message": "UserRole updated",
    "responseId": "d6c1dd77-6cdc-4459-b046-d4251cbcfbdd",
    "status": 200,
    "timestamp": "07-09-2026 12:13:12"
}
```

5. **Delete User**

**DELETE** localhost:9194/v2/tour/users/user-role/ben@mail.com

Response

204 No Content
