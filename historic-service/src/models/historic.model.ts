import { Prop, Schema, SchemaFactory } from "@nestjs/mongoose";
import { HydratedDocument } from "mongoose";

export type HistoricDocument = HydratedDocument<HistoricModel>;

@Schema()
export class HistoricModel{
    @Prop()
    service: string;
    @Prop()
    change: string;
    @Prop()
    changerId: string;
}

export const HistoricSchema = SchemaFactory.createForClass(HistoricModel);