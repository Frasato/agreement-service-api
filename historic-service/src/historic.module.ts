import { Module } from "@nestjs/common";
import { MongooseModule } from "@nestjs/mongoose";
import { HistoricModel, HistoricSchema } from "./models/historic.model";
import { HistoricService } from "./services/historic.service";
import { HistoricController } from "./controller/historic.controller";

@Module({
    imports: [MongooseModule.forFeature([
        {
            name: HistoricModel.name,
            schema: HistoricSchema
        }
    ])],
    controllers: [HistoricController],
    providers: [HistoricService]
})
export class HistoricModule{}