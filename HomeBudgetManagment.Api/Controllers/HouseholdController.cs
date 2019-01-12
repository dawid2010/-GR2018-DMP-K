using AutoMapper;
using HomeBudgetManagment.Api.Models.Interfaces;
using HomeBudgetManagment.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace HomeBudgetManagment.Api.Controllers
{
    public class HouseholdController : ApiController
    {
        IMapper mapper;
        IMapperConfProvider confProvider;
        public HouseholdController(IMapperConfProvider confProvider)
        {
            this.confProvider = confProvider;
            var cfg = confProvider.ProvideMapperConfiguration();
            mapper = cfg.CreateMapper(); 
        }
        [HttpGet]
        public HouseholdDTO GetHousehold(int Id)
        {


            return null;
        }


    }
}
