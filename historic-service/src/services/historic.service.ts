import { HttpException, HttpStatus, Injectable } from "@nestjs/common";
import { InjectModel } from "@nestjs/mongoose";
import { Model } from "mongoose";
import { InvalidParamsException } from "src/exceptions/invalid-params.exception";
import { HistoricModel } from "src/models/historic.model";

@Injectable()
export class HistoricService{
    constructor(
        @InjectModel(HistoricModel.name)
        private readonly historic: Model<HistoricModel>
    ){}

    async create(data: {service: string, change: string, changerId: string}): Promise<void>{
        if(data.change == null || data.change == null || data.changerId == null){
            throw new InvalidParamsException();
        }
        const createdHistoric = new this.historic(data);
        await createdHistoric.save();
    }

    async findAll(): Promise<HistoricModel[]>{
        return this.historic.find().exec();
    }
}