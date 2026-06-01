import { Injectable } from "@nestjs/common";
import { InjectModel } from "@nestjs/mongoose";
import { Model } from "mongoose";
import { HistoricModel } from "src/models/historic.model";

@Injectable()
export class HistoricService{
    constructor(
        @InjectModel(HistoricModel.name)
        private readonly historic: Model<HistoricModel>
    ){}

    async create(data: {service: string, change: string, changerId: string}): Promise<void>{
        const createdHistoric = new this.historic(data);
        await createdHistoric.save();
    }

    async findAll(): Promise<HistoricModel[]>{
        return this.historic.find().exec();
    }
}