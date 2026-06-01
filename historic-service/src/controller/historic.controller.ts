import { Controller, Get } from "@nestjs/common";
import { Ctx, MessagePattern, Payload, RmqContext } from "@nestjs/microservices";
import { HistoricModel } from "src/models/historic.model";
import { HistoricService } from "src/services/historic.service";

@Controller('historic')
export class HistoricController{
    constructor(
        private historicService: HistoricService
    ){}

    @MessagePattern()
    getNotifications(
        @Payload() data: {service: string, change: string, changerId: string},
        @Ctx() context: RmqContext
    ){
        this.historicService.create(data);
    }

    @Get()
    async findAll(): Promise<HistoricModel[]>{
        return this.historicService.findAll();
    }
}