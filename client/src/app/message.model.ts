export interface Message {
    id?: number;
    sender: { id: number , firstName?:String };
    receiver: { id: number };
    message: string;
    createdAt?: Date;
  }