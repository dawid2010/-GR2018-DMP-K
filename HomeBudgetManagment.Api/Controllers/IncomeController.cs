using AutoMapper;
using HomeBudgetManagment.Api.DAL;
using HomeBudgetManagment.Api.DAL.Entities;
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
                    return Json(dto);
                }
            }

        }
        [HttpGet]
        public IHttpActionResult GetIncomes()
        {
            using (var context = new HomeBudgetDbContext())
            {
                var incomes = context.Incomes.ToList();
                var dto = mapper.Map<List<IncomeDTO>>(incomes);
                return Json(dto);
            }


        }

        [HttpPost]
        public IHttpActionResult AddIncome([FromBody] IncomeDTO incomeDTO)
        {
            using (var context = new HomeBudgetDbContext())
            {
                var entity = mapper.Map<Income>(incomeDTO);
                context.Incomes.Add(entity);
                return Json(entity);

            }
        }

        [HttpPost]
        public IHttpActionResult AddIncomeToUser(int UserId,[FromBody] IncomeDTO incomeDTO)
        {

            using (var context = new HomeBudgetDbContext())
            {
                var user = context.Users.FirstOrDefault(x => x.Id == UserId);
                var incomeToAdd = mapper.Map<Income>(incomeDTO);
                user.Incomes.Add(incomeToAdd);
                context.SaveChanges();
                return Json(incomeDTO);
            }

        }

    }
}
