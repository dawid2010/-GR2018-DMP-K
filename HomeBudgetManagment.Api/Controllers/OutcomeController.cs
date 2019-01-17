using AutoMapper;
using HomeBudgetManagment.Api.DAL;
using HomeBudgetManagment.Api.DAL.Entities;
using HomeBudgetManagment.Api.Models.Implementations;
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
      
        public OutcomeController()
        {
            var confProvider = new MapperConfProvider();
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
                    return Json(dto);
                }    
            }
                


        }

        [HttpGet]
        public IHttpActionResult GetOutcomes()
        {
            using (var ctx = new HomeBudgetDbContext())
            {
                var outcomes = ctx.Outcomes.ToList();
                var dto = mapper.Map<List<OutcomeDTO>>(outcomes);
                return Json(dto);
            }

        }

        [HttpPost]
        public void AddOutcome([FromBody]OutcomeDTO outcomeDTO)
        {
            using (var context = new HomeBudgetDbContext())
            {
                var entity = mapper.Map<Outcome>(outcomeDTO);
                context.Outcomes.Add(entity);
                context.SaveChanges();
            }

        }


        [HttpPost]
        public IHttpActionResult AddOutcomeToUser(int UserId, [FromBody] OutcomeDTO outcomeDTO)
        {

            using (var context = new HomeBudgetDbContext())
            {
                var user = context.Users.FirstOrDefault(x => x.Id == UserId);
                var outcomeToAdd = mapper.Map<Outcome>(outcomeDTO);
                user.Outcomes.Add(outcomeToAdd);
                context.SaveChanges();
                return Json(outcomeDTO);
            }

        }

    }
}
