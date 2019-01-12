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
    public class OutcomeController : ApiController
    {
        IMapper mapper;
      
        public OutcomeController(IMapperConfProvider confProvider)
        {
            var cfg = confProvider.ProvideMapperConfiguration();
            mapper = cfg.CreateMapper();

        }
        [HttpGet]
        public IHttpActionResult GetOutcome(int Id)
        {
            using (var context = new HomeBudgetDbContext())
            {
                var outcome = context.Outcomes.FirstOrDefault(x => x.Id == Id);
                if(outcome == null)
                {
                    return NotFound();
                }
                else
                {
                    var dto = mapper.Map<OutcomeDTO>(outcome);
                    return Ok(dto);
                }    
            }
                


        }

        [HttpGet]
        public IHttpActionResult GetAll()
        {
            using (var ctx = new HomeBudgetDbContext())
            {
                var outcomes = ctx.Outcomes.ToList();
                var dto = mapper.Map<List<OutcomeDTO>>(outcomes);
                return Ok(dto);
            }

        }

    }
}
