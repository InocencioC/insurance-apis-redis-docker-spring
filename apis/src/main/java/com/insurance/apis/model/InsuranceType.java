package com.insurance.apis.model;

public enum InsuranceType {
    
    // Health Insurance
    LIFE_INSURANCE("Life Insurance","Protects against financial loss due to death or disability."),
    HEALTH_INSURANCE("Health Insurance", "Covers medical expenses like consultations, exams, and surgeries."),
    PERSONAL_ACCIDENT_INSURANCE("Personal Accident Insurance", "Provides compensation for injury, disability, or death from an accident."),
    WORK_ACCIDENT_INSURANCE("Work Accident Insurance", "Mandatory for companies, protects employees in case of work-related accidents."),

    //  Property Insurance
    CAR_INSURANCE("Car Insurance", "Mandatory, covers damages caused by/to vehicles, including third-party liability."),
    HOME_INSURANCE("Home Insurance", "Protects homes and contents against various risks like fire, theft, and water damage."),
    FIRE_INSURANCE("Fire Insurance", "Mandatory for apartments and financed homes, covers fire damage."),
    TRAVEL_INSURANCE("Travel Insurance", "Covers unforeseen events during travel, such as medical emergencies or lost luggage."),
    RECREATIONAL_BOAT_INSURANCE("Recreational Boat Insurance", "Protects boats, jet skis, and other vessels."),
    ELECTRONIC_EQUIPMENT_INSURANCE("Electronic Equipment Insurance", "Covers damage or theft of electronic devices."),

    // Liability Insurance
    GENERAL_LIABILITY_INSURANCE("General Liability Insurance", "Covers damages caused to third parties by the insured (person or company)."),
    PROFESSIONAL_LIABILITY_INSURANCE("Professional Liability Insurance", "Covers damages caused to clients in the exercise of a professional activity."),
    HUNTING_FISHING_LIABILITY_INSURANCE("Hunting & Fishing Liability Insurance", "Mandatory for hunting and fishing activities."),
    DANGEROUS_DOG_LIABILITY_INSURANCE("Dangerous Dog Liability Insurance", "Mandatory for owners of dangerous or potentially dangerous dog breeds."),

    // Other Types of Insurance 
    LEGAL_PROTECTION_INSURANCE("Legal Protection Insurance", "Covers legal expenses in case of disputes."),
    PET_INSURANCE("Pet Insurance", "Covers veterinary expenses and civil liability for pets."),
    CAPITALIZATION_SAVINGS_INSURANCE("Capitalization/Savings Insurance", "Combines protection with a savings or investment aspect."),
    RENT_GUARANTEE_INSURANCE("Rent Guarantee Insurance", "Protects landlords or tenants against non-payment of rent."),
    DEFAULT_INSURANCE("Default Insurance", "Guarantees compensation to the creditor in case of payment default.");

    private final String displayName;
    private final String description;

    private InsuranceType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    public String getDisplayName() {
        return displayName;
    }
    public String getDescription() {
        return description;
    }

}
