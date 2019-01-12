namespace HomeBudgetManagment.Api.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class initial : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Households",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Code = c.String(),
                        Description = c.String(),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.Users",
                c => new
                    {
                        Id = c.Int(nullable: false),
                        UserCredentialsId = c.Int(nullable: false),
                        UserInfoId = c.Int(nullable: false),
                        HouseholdId = c.Int(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.Households", t => t.HouseholdId)
                .ForeignKey("dbo.UserCredentials", t => t.Id)
                .ForeignKey("dbo.UserInfoes", t => t.Id)
                .Index(t => t.Id)
                .Index(t => t.HouseholdId);
            
            CreateTable(
                "dbo.Incomes",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        IncomeValue = c.Double(nullable: false),
                        IncomeTypeId = c.Int(),
                        User_Id = c.Int(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.IncomeTypes", t => t.IncomeTypeId)
                .ForeignKey("dbo.Users", t => t.User_Id)
                .Index(t => t.IncomeTypeId)
                .Index(t => t.User_Id);
            
            CreateTable(
                "dbo.IncomeTypes",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Description = c.String(),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.Outcomes",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        OutcomeTypeId = c.Int(nullable: false),
                        OutcomeValue = c.Double(nullable: false),
                        User_Id = c.Int(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.OutcomeTypes", t => t.OutcomeTypeId, cascadeDelete: true)
                .ForeignKey("dbo.Users", t => t.User_Id)
                .Index(t => t.OutcomeTypeId)
                .Index(t => t.User_Id);
            
            CreateTable(
                "dbo.OutcomeTypes",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Description = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.UserCredentials",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Login = c.String(),
                        Password = c.String(),
                        UserId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.UserInfoes",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        FirstName = c.String(),
                        LastName = c.String(),
                        UserId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.Targets",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Description = c.String(),
                    })
                .PrimaryKey(t => t.Id);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Users", "Id", "dbo.UserInfoes");
            DropForeignKey("dbo.Users", "Id", "dbo.UserCredentials");
            DropForeignKey("dbo.Outcomes", "User_Id", "dbo.Users");
            DropForeignKey("dbo.Outcomes", "OutcomeTypeId", "dbo.OutcomeTypes");
            DropForeignKey("dbo.Incomes", "User_Id", "dbo.Users");
            DropForeignKey("dbo.Incomes", "IncomeTypeId", "dbo.IncomeTypes");
            DropForeignKey("dbo.Users", "HouseholdId", "dbo.Households");
            DropIndex("dbo.Outcomes", new[] { "User_Id" });
            DropIndex("dbo.Outcomes", new[] { "OutcomeTypeId" });
            DropIndex("dbo.Incomes", new[] { "User_Id" });
            DropIndex("dbo.Incomes", new[] { "IncomeTypeId" });
            DropIndex("dbo.Users", new[] { "HouseholdId" });
            DropIndex("dbo.Users", new[] { "Id" });
            DropTable("dbo.Targets");
            DropTable("dbo.UserInfoes");
            DropTable("dbo.UserCredentials");
            DropTable("dbo.OutcomeTypes");
            DropTable("dbo.Outcomes");
            DropTable("dbo.IncomeTypes");
            DropTable("dbo.Incomes");
            DropTable("dbo.Users");
            DropTable("dbo.Households");
        }
    }
}
