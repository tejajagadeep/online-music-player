export class AuthAccessToken {

    constructor(
    public message: string,
    public jwtToken: string,
    public role: string,
    public username: string
    ) {}
}