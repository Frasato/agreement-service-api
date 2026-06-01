import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { HistoricModule } from './historic.module';

@Module({
  imports: [
    MongooseModule.forRoot('mongodb://localhost:27017/historicDataBase',),
    HistoricModule
  ],
})
export class AppModule {}
