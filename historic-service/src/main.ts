import { NestFactory } from '@nestjs/core';
import { Transport } from '@nestjs/microservices';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  app.connectMicroservice({
    transport: Transport.RMQ,
    options: {
      urls: ['amqp://admin:admin@localhost:5672'],
      queue: 'historic-request-queue',
      queueOptions: {
        durable: true,
      },
    },
  });

  await app.startAllMicroservices();

  await app.listen(3001);
}

bootstrap();