using AutoMapper;
using HomeBudgetManagment.Api.DAL.Entities;
using HomeBudgetManagment.Api.Infrastructure;
using HomeBudgetManagment.Api.Models.Interfaces;
using HomeBudgetManagment.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeBudgetManagment.Api.Models.Implementations
{
    public class MapperConfProvider : IMapperConfProvider
    {
        public MapperConfiguration ProvideMapperConfiguration()
        {
            var config = new MapperConfiguration(cfg =>
            {
                cfg.CreateMap<Household, HouseholdDTO>();
                cfg.CreateMap<HouseholdDTO, Household>();

                cfg.CreateMap<Income, IncomeDTO>();
                cfg.CreateMap<IncomeDTO, Income>();

                cfg.CreateMap<IncomeType, IncomeTypeDTO>();
                cfg.CreateMap<IncomeTypeDTO, IncomeType>();

                cfg.CreateMap<Outcome, OutcomeDTO>();
                cfg.CreateMap<OutcomeDTO, Outcome>();

                cfg.CreateMap<OutcomeType, OutcomeTypeDTO>();
                cfg.CreateMap<OutcomeTypeDTO, OutcomeType>();

                cfg.CreateMap<Target, TargetDTO>();
                cfg.CreateMap<TargetDTO, Target>();

                cfg.CreateMap<User, UserDTO>();
                cfg.CreateMap<UserDTO, User>();

                cfg.CreateMap<UserCredentials, UserCredentialsDTO>();
                cfg.CreateMap<UserCredentialsDTO, UserCredentials>();

                cfg.CreateMap<UserInfo, UserInfoDTO>();
                cfg.CreateMap<UserInfoDTO, UserInfo>();

            });



            return config;
        }
    }
}