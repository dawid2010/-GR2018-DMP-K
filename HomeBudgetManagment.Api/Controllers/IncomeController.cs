using AutoMapper;
using HomeBudgetManagment.Api.DAL;
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
    public class IncomeController : ApiController
    {
        IMapper mapper;
        public IncomeController(IMapperConfProvider confProvider)
        {
            var cfg = confProvider.ProvideMapperConfiguration();
            mapper = cfg.CreateMapper();
        }

        [HttpGet]
        public IHttpActionResult GetIncome(int Id)
        {
            using (var context = new HomeBudgetDbContext())
            {
                var income = context.Incomes.FirstOrDefault(x => x.Id == Id);
                if(income == null)
                {
                    return NotFound();
                }
                else
                {
                    var dto = mapper.Map<IncomeDTO>(income);
                    return Ok(dto);
                }
            }

        }



    }
}
