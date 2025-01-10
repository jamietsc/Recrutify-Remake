using Recrutify.DataAccessLayer.Data;
using Recrutify.DataAccessLayer.Repositories;
using Recrutify.DataAccessLayer.SqlDataAccess;
using Recrutify.Models;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorPages();
builder.Services.AddServerSideBlazor();
builder.Services.AddScoped<HelperModel>();
//builder.Services.AddSingleton<WeatherForecastService>();

//Repositories


//Data
builder.Services.AddScoped<IBewerber<BewerberModel>, BewerberData>();
builder.Services.AddScoped<IMultipleChoice<MultipleChoiceModel>, MultipleChoiceData>();
builder.Services.AddScoped<IAdmin<AdminModel>, AdminData>();
builder.Services.AddScoped<IBewerberTest<BewerberTestModel>, BewerberTestData>();
builder.Services.AddScoped<IUnternehmen<TestModel>, UnternehmenData>();

//SqlAccess
builder.Services.AddScoped<ISqlDataAccess, SqlDataAccess>();

//Models
builder.Services.AddScoped<BewerberModel>();
builder.Services.AddScoped<MultipleChoiceModel>();
builder.Services.AddScoped<BewerberTestModel>();
builder.Services.AddScoped<TestModel>();
builder.Services.AddScoped<AdminModel>();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();

app.UseStaticFiles();

app.UseRouting();

app.MapBlazorHub();
app.MapFallbackToPage("/_Host");

app.Run();