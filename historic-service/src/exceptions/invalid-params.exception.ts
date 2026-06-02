import { HttpException, HttpStatus } from "@nestjs/common";

export class InvalidParamsException extends HttpException{
    constructor(){
        super({
            timestamp: Date.now().toString(),
            message: "Some params are null or empty!",
            status: HttpStatus.BAD_REQUEST
        }, HttpStatus.BAD_REQUEST)
    }
}