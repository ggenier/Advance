/**
 * Advance management
 * Advance project
 *
 * OpenAPI spec version: 0.0.1
 * Contact: ggenier@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import { Adress } from './adress';
import { TrainingCenter } from './trainingCenter';


export interface Training { 
    id?: number;
    trainingType: Training.TrainingTypeEnum;
    comment?: string;
    certifying: boolean;
    gettrainingAdress: Adress;
    dateDeDebut?: string;
    dateDeFin?: string;
    organisme?: TrainingCenter;
}
export namespace Training {
    export type TrainingTypeEnum = 'TOIT' | 'ENDU' | 'HUIS';
    export const TrainingTypeEnum = {
        TOIT: 'TOIT' as TrainingTypeEnum,
        ENDU: 'ENDU' as TrainingTypeEnum,
        HUIS: 'HUIS' as TrainingTypeEnum
    };
}